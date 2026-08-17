<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>register</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="assets/css/styles.css">
</head>
<body>
    <?php
        include("functions.php");
        echo '<div class="panel panel-primary">';
        echo '<div class="panel-heading">Register New Account</div>';
        echo '<div class="panel-body">';
        if (isset($_GET['err']))
        {
            if ($_GET['err'] == "empty")
                echo '<h2>Please fill out all fields!</h2>';
            else if ($_GET['err'] == "userExists")
                echo '<h2>Username already exists!</h2>';
        }
        if (!isset($_POST['submit']))
        {
            echo '<form method="post" action="register.php">
                    <div class="form-group">
                        <label class="control-label">Create Username:</label>
                        <input type="text" class="form-control" name="username">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Create Password:</label>
                        <input type="password" class="form-control" name="password">
                    </div>
                    <div class="form-group">
                        <input class="btn btn-success" name="submit" type="submit" value="submit" />
                    </div>
                  </form>
                  <p>If you already have an account you can login heree: <a href="login.php">Login here</a></p>';
        }
        else
        {
            $username=addslashes($_POST['username']);
            $password=$_POST['password'];
            if ($username==NULL || $password==NULL)
            {
                redirect("register.php?err=empty");
            }
            $dblink=dbConnect("contact_data");
            $sql="Select * from `accounts` where `username`='$username'";
            $result=$dblink->query($sql) or
                die("Something went wrong with $sql");
            if ($result->num_rows > 0)
            {
                redirect("register.php?err=userExists");
            }
            else
            {
                $salt='CS4413SU26';
                $hashPw=hash('sha256',$salt.$password);
                $sql="Insert into `accounts` (`username`, `pw_hash`, `session_id`) values ('$username', '$hashPw', '')";
                $dblink->query($sql) or
                    die("Something went wrong with $sql");
                redirect("login.php?msg=registered");
            }
        }
        echo '</div>';
        echo '</div>';
    ?>
</body>
</html>
