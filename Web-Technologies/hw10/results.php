<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Contact Form Results</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body>
    <div class="site-shell">
        <header class="site-header" id="top">
            <div class="brand-bar">
                <h1>Welcome to my Website!</h1>
            </div>

            <nav class="site-nav" aria-label="Main">
                <a href="index.html">Home</a>
                <a href="hobbies.html">Hobbies</a>
                <a href="school.html">School</a>
                <a href="work.html">Work</a>
                <a href="contact.html" class="active" aria-current="page">Contact</a>
            </nav>
        </header>

        <main class="site-main">
            <section class="page-panel section-stack">
                <div class="content-card">
                    <h2 class="page-title">Contact Form Data</h2>
                    <?php
                        if (!isset($_GET['submit'])) {
                            echo '<h3>Error: form was not submitted.</h3>';
                        } else {
                            $missing = '';
                            $hasMissing = false;
                            // trim to remove beginning and trailing whitespace
                            // .= for concatenation
                            if (!isset($_GET['firstName']) || trim($_GET['firstName']) === '') {
                                $missing .= 'firstName ';
                                $hasMissing = true;
                            }

                            if (!isset($_GET['lastName']) || trim($_GET['lastName']) === '') {
                                $missing .= 'lastName ';
                                $hasMissing = true;
                            }

                            if (!isset($_GET['userName']) || trim($_GET['userName']) === '') {
                                $missing .= 'userName ';
                                $hasMissing = true;
                            }

                            if (!isset($_GET['passWord']) || trim($_GET['passWord']) === '') {
                                $missing .= 'passWord ';
                                $hasMissing = true;
                            }

                            if ($hasMissing) {
                                echo '<h3>Error: the following fields are missing or empty: '.htmlspecialchars(trim($missing)).'</h3>';
                            } else {
                                $fn = $_GET['firstName'];
                                $ln = $_GET['lastName'];
                                $un = $_GET['userName'];
                                $pw = $_GET['passWord'];

                                echo '<p><strong>First Name:</strong> '.htmlspecialchars($fn).'</p>';
                                echo '<p><strong>Last Name:</strong> '.htmlspecialchars($ln).'</p>';
                                echo '<p><strong>Username:</strong> '.htmlspecialchars($un).'</p>';
                                echo '<p><strong>Password:</strong> '.htmlspecialchars($pw).'</p>';

                                if ($pw === "abc999") {
                                    echo '<h3>Welcome Admin!</h3>';
                                }
                            }
                        }
                    ?>
                </div>

                <a class="back-to-top" href="#top">Back to Top</a>
            </section>
        </main>
    </div>
    <script src="assets/js/validation.js"></script>
</body>

</html>