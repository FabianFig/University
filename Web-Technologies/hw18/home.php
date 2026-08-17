<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Authenticated Home Page</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="assets/css/styles.css">
</head>
<body>
    <?php
        include("functions.php");
        if (!isset($_GET['sid']) || $_GET['sid']==NULL)
            die('<h2>Invalid or missing session id!</h2>');
        else
        {
            $sid=addslashes($_GET['sid']);
            $dblink=dbConnect("contact_data");
            $sql="Select * from `accounts` where `session_id`='$sid'";
            $result=$dblink->query($sql) or
                die("Something went wrong with the sql $sql");
            if ($result->num_rows<=0)
                echo '<h2>Invalid session id!</h2>';
            else
                echo '<h2>Welcome to thhe secure site!</h2>';
        }
    ?>
</body>
</html>