<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="assets/css/styles.css">
</head>
<body>
    <?php
        include("functions.php");
        echo '<div class="panel panel-primary">';
        echo '<div class="panel-heading">Login for Access</div>';
        echo '<div class="panel-body">';
        if (isset($_GET['msg']))
        {
            if ($_GET['msg'] == "registered")
                echo '<h2>Account created successfully! Please login.</h2>';
        }
        if (isset($_GET['err']))
        {
            if ($_GET['err'] == "invalidLogin")
                echo '<h2>Incorrect login information!</h2>';
            else if ($_GET['err'] == "missingSid")
                echo '<h2>Invalid or missing session id!</h2>';
            else if ($_GET['err'] == "invalidSid")
                echo '<h2>Invalid session id!</h2>';
        }
        if (!isset($_POST['submit']))
        {
            echo '<form method="post" action="login.php">
                    <div class="form-group">
                        <label class="control-label">Username:</label>
                        <input type="text" class="form-control" name="username">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Password:</label>
                        <input type="password" class="form-control" name="password">
                    </div>
                    <div class="form-group">
                        <input class="btn btn-success" name="submit" type="submit" value="submit" />
                    </div>
                  </form>
                  <p>Need an account? <a href="register.php">Register here</a></p>';
        }
        else
        {
            $username=addslashes($_POST['username']);
            $salt='CS4413SU26';
            $sidSalt=microtime();
            $password=$_POST['password'];
            $hashPw=hash('sha256',$salt.$password);
            $dblink=dbConnect("contact_data");
            $sql="Select * from `accounts` where `username`='$username' and `pw_hash`='$hashPw'";
            $result=$dblink->query($sql) or
                die("Something went wrong with $sql");
            if ($result->num_rows <= 0)
            {
                redirect("login.php?err=invalidLogin");
            }
            else
            {
                $data=$result->fetch_array(MYSQLI_ASSOC);
                $sid=hash('sha256',$sidSalt.$username.$password);
                $sql="Update `accounts` set `session_id`='$sid' where `username`='$username'";
                $dblink->query($sql) or
                    die("something wrong with $sql");
                redirect("results.php?sid=$sid");
            }
        }
        echo '</div>';
        echo '</div>';
    ?>
</body>
</html>