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
                <a href="contact.php" class="active" aria-current="page">Contact</a>
            </nav>
        </header>

        <main class="site-main">
            <section class="page-panel section-stack">
                <div class="content-card">
                    <h2 class="page-title">Contact Form</h2>
                    <?php
                        $fn = '';
                        $ln = '';
                        $un = '';
                        $pw = '';
                        $missing = '';
                        $hasMissing = false;

                        if (!isset($_POST['submit'])) {
                            header('Location: contact.php?err=notSubmitted');
                            exit;
                        }

                        if (isset($_POST['firstName'])) {
                            $fn = trim($_POST['firstName']);
                        }

                        if (isset($_POST['lastName'])) {
                            $ln = trim($_POST['lastName']);
                        }

                        if (isset($_POST['userName'])) {
                            $un = trim($_POST['userName']);
                        }

                        if (isset($_POST['passWord'])) {
                            $pw = trim($_POST['passWord']);
                        }

                        if ($fn === '') {
                            $missing .= 'firstName ';
                            $hasMissing = true;
                        }
                        if ($ln === '') {
                            $missing .= 'lastName ';
                            $hasMissing = true;
                        }
                        if ($un === '') {
                            $missing .= 'userName ';
                            $hasMissing = true;
                        }
                        if ($pw === '') {
                            $missing .= 'passWord ';
                            $hasMissing = true;
                        }

                        if ($hasMissing) {
                            echo '<div class="alert alert-danger">Error: The following fields are missing or empty: '.htmlspecialchars(trim($missing)).'</div>';
                        } else {
                            echo '<p><strong>First Name:</strong> '.htmlspecialchars($fn).'</p>';
                            echo '<p><strong>Last Name:</strong> '.htmlspecialchars($ln).'</p>';
                            echo '<p><strong>Username:</strong> '.htmlspecialchars($un).'</p>';
                            echo '<p><strong>Password:</strong> '.htmlspecialchars($pw).'</p>';

                            if ($pw === "abc999") {
                                echo '<h3>Welcome Admin!</h3>';
                            }
                        }
                    ?>
                </div>
                <a class="back-to-top" href="#top">Back to Top</a>
            </section>
        </main>
    </div>
</body>
</html>