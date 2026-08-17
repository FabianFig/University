# Group 19

## Smart task tracker with MongoDB

Flask + MongoDB app for creating, updating, and tracking tasks (title, description, status, deadline).

## Setup

    cp .env.example .env
    # fill in MONGO_URI and FLASK_SECRET_KEY in .env


    (can create a python venv beforehand)
    pip install -r requirements.txt

    # make sure MongoDB is running locally with:
    docker run -d -p 27017:27017 --name mongo-dev mongo

    then:
    python app.py

Then open `http://localhost:5000`.

## What's done

- `app.py` with Flask routes at: `/`, `/tasks/new`, `/tasks/<id>/edit`, `/tasks/<id>/delete`
- MongoDB connection and flask secret key are read from `.env` (see `.env.example` for how it should be setup)
- CRUD logic: create, list/filter by status, edit, delete
- Deadline validation (rejects malformed dates, flashes an error instead of saving bad data)
- Invalid/missing task IDs handled gracefully (redirect + flash message instead of a 500)
- `templates/` — styled with basic CSS (status badges, cards, forms)
- Task document shape: `{title, description, status, deadline, created_at}`

## TODO

- [ ] Deploy: MongoDB (Atlas or self-hosted) + AWS/GCP VM
- [ ] Switch `debug=False` and bind `0.0.0.0` for the VM
- [ ] Full click-through test once deployed
- [ ] Screenshots + code snippets for the report
- [ ] Report sections + Contributions
- [ ] Demo video (Panopto, 10+ min)

## Sources

I have added some of the main sources I used in comments at the top of `app.py` and inside each of the templates in `templates/`.
