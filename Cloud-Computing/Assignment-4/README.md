# Assignment 4
Flask app for uploading images to an AWS S3 bucket, with grayscale conversion and resizing before upload.

## Highlights

- Upload, list, serve, and delete image objects in an S3 bucket via `app.py`.
- Converts uploads to grayscale and resizes images wider than 800px.
- `index.html` template for the browser UI.

## Run / Build

- Configure AWS credentials (EC2 IAM role or CLI) and set the S3 bucket name in `app.py`, then run with Python.