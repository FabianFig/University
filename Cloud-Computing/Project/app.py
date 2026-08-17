'''
Smart task tracker with MongoDB
Group 19 - Abrar Ahmed and Fabian Figueroa

Some sources I found that I used to help woth this:
    https://flask.palletsprojects.com/en/latest/quickstart/ 
    https://pymongo.readthedocs.io/en/stable/ 
    https://www.mongodb.com/docs/languages/python/pymongo-driver/current/integrations/flask-celery-integration/
    https://www.mongodb.com/docs/manual/crud/ 

'''
from datetime import datetime, UTC
from zoneinfo import ZoneInfo
from flask import Flask, render_template, request, redirect, url_for, flash
from pymongo import MongoClient
from bson.objectid import ObjectId
from bson.errors import InvalidId
import os
from dotenv import load_dotenv

load_dotenv()

app = Flask(__name__)
app.secret_key = os.environ["FLASK_SECRET_KEY"]

# points at MongoDB Atlas or self-hosted. set in the .env
MONGO_URI =os.environ["MONGO_URI"]
client = MongoClient(MONGO_URI)
db = client["task_tracker"]
tasks_col = db["tasks"]

STATUSES = ["To Do", "In Progress", "Done"]

def validate_deadline(raw):
    # returns the validate deadline string or raises valueError
    if not raw:
        return ""
    try:
        datetime.strptime(raw, "%Y-%m-%d")
    except ValueError:
        raise ValueError("Invbalid deadline format")
    return raw


@app.route("/")
def index():
    # listing all of the tasks(can be filtered by status)
    status_filter = request.args.get("status")
    query = {"status": status_filter} if status_filter else {}
    tasks = list(tasks_col.find(query).sort("deadline", 1))
    return render_template("index.html", tasks=tasks, statuses=STATUSES, active_filter=status_filter)


@app.route("/tasks/new", methods=["GET", "POST"])
def create_task():
    if request.method == "POST":
        try:
            deadline = validate_deadline(request.form.get("deadline", ""))
        except ValueError:
            flash("Please enter a valid deadline date.")
            return redirect(url_for("create_task"))

        task = {
            "title": request.form.get("title", "").strip(),
            "description": request.form.get("description", "").strip(),
            "status": request.form.get("status", "To Do"),
            "deadline": deadline,
            "created_at": datetime.now(ZoneInfo("America/Chicago")),
        }
        if not task["title"]:
            flash("Title is required.")
            return redirect(url_for("create_task"))
        tasks_col.insert_one(task)
        return redirect(url_for("index"))
    return render_template("task_form.html", statuses=STATUSES, task=None)


@app.route("/tasks/<task_id>/edit", methods=["GET", "POST"])
def edit_task(task_id):
    try:
        oid = ObjectId(task_id)
    except InvalidId:
        flash("That task does not exist.")
        return redirect(url_for("index"))

    if request.method == "POST":
        try:
            deadline = validate_deadline(request.form.get("deadline", ""))
        except ValueError:
            flash("Please enter a valid deadline date.")
            return redirect(url_for("edit_task", task_id=task_id))
        updates = {
            "title": request.form.get("title", "").strip(),
            "description": request.form.get("description", "").strip(),
            "status": request.form.get("status", "To Do"),
            "deadline": deadline,
        }
        tasks_col.update_one({"_id": oid}, {"$set": updates})
        return redirect(url_for("index"))

    task = tasks_col.find_one({"_id": oid})
    if task is None:
        flash("That task doesn't exist.")
        return redirect(url_for("index"))
    return render_template("task_form.html", statuses=STATUSES, task=task)


@app.route("/tasks/<task_id>/delete", methods=["POST"])
def delete_task(task_id):
    try:
        oid = ObjectId(task_id)
    except InvalidId:
        flash("That task does not exist.")
        return redirect(url_for("index"))
    tasks_col.delete_one({"_id": oid})
    return redirect(url_for("index"))


if __name__ == "__main__":
    # TODO (done by whomever is the deployment owner): switch debug=False and then please bind 0.0.0.0 for theVM
    app.run(debug=True)
