<?php
if (session_status() === PHP_SESSION_NONE) {
    session_start();
}

if (!function_exists('redirect')) {
    function redirect($url) {
        header("Location: " . $url);
        exit;
    }
}
?>
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
                <?php
                    $page = 'contact';
                    include("navigation.php");
                ?>
            </nav>
        </header>

        <main class="site-main">
            <section class="page-panel section-stack">
                <div class="content-card">
                    <h2 class="page-title">Contact Form Results</h2>
                    <?php
                        if (!isset($_POST['submit'])) {
                            redirect("index.php?page=contact&err=notSubmitted");
                        }
                        else
                        {
                            $errors="";
                            $fn=$_POST['firstName'];
                            if ($fn==NULL)
                                $errors.="fnNull";
                            if (!ctype_alpha($fn))
                                $errors.="fnInvalid";
                            $_SESSION['fn']=$fn;
                            $ln=$_POST['lastName'];
                            if ($ln==NULL)
                                $errors.="lnNull";
                            if (!ctype_alpha($ln))
                                $errors.="lnInvalid";
                            if ($errors!=NULL)
                                redirect("index.php?page=contact&err=$errors");
                            $un=$_POST['userName'];
                            $pw=$_POST['passWord'];
                            $phone=$_POST['phone'];
                            $email=$_POST['email'];
                            $comments=$_POST['comments'];

                            echo '<p><strong>First Name:</strong> '.htmlspecialchars($fn).'</p>';
                            echo '<p><strong>Last Name:</strong> '.htmlspecialchars($ln).'</p>';
                            echo '<p><strong>Email:</strong> '.htmlspecialchars($email).'</p>';
                            echo '<p><strong>Phone:</strong> '.htmlspecialchars($phone).'</p>';
                            echo '<p><strong>Username:</strong> '.htmlspecialchars($un).'</p>';
                            echo '<p><strong>Password:</strong> '.htmlspecialchars($pw).'</p>';
                            echo '<p><strong>Comments:</strong> '.htmlspecialchars($comments).'</p>';

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