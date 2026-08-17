<?php
    include("functions.php");
    if (!isset($_GET['page']))
        $page="home";
    else
        $page=$_GET['page'];

    $dblink=dbConnect("cms");
    $title_sql="SELECT `title` FROM `pages` WHERE `location`='$page'";
    $title_result=$dblink->query($title_sql);
    $title_data=$title_result->fetch_array(MYSQLI_ASSOC);
    $page_title=$title_data['title'];
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><?php echo $page_title;?></title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="assets/css/styles.css">
    <script src="assets/js/jquery-3.5.1.js"></script>
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
                        $dblink=dbConnect("cms");
                        $sql="SELECT `auto_id`, `location`, `title`, `data` FROM `pages` WHERE `location`='$page'";
                        $result=$dblink->query($sql) or
                            die("Something went wrong with $sql");
                        $data=$result->fetch_array(MYSQLI_ASSOC);
                        
                        if(str_contains($data['data'],'<?php'))
                            eval('?>'.$data['data']);
                        else
                            echo $data['data'];
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