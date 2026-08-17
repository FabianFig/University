<?php
    if (!isset($_GET['page'])) //first page load/visit
        $page="home";
    else
        $page=$_GET['page'];
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Fabian's <?php echo ucfirst($page);?> Page.</title>
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
                    include("navigation.php");
                ?>
            </nav>
        </header>

        <main class="site-main">
            <section class="page-panel hero-panel">
                <div class="content-card">
                    <?php
                        switch($page){
                            case "hobbies":
                                include("hobbies.php");
                                break;
                            case "school":
                                include("school.php");
                                break;
                            case "work":
                                include("work.php");
                                break;
                            case "results":
                                include("results.php");
                                break;
                            case "contact":
                                include("contact.php");
                                break;
                            default:
                                include("home.php");
                                break;
                        }
                    ?>
                </div>

                <figure class="content-card photo-card">
                    <h3>Statue of Lenin (Found near UTSA Data Science)</h3>
                    <img src="assets/images/20260408_0032.png" alt="Photo of the new UTSA San Pedro building"
                        title="Vladimir Lenin Statue near DT UTSA Campus" width="400">
                </figure>
            </section>

            <a class="back-to-top" href="#top">Back to Top</a>
        </main>
    </div>

</body>

</html>