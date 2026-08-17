<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Contact Form Results</title>
    </head>
    <body>
        <h2>Contact Form Data:</h2>
        <?php
            if (isset($_POST['userName']) && $_POST['userName']!==NULL)
                $un=$_POST['userName'];
            else 
                $un=NULL;

            if (isset($_POST['passWord']) && $_POST['passWord'] !== NULL)
                $pw=$_POST['passWord'];
            else
                $pw=NULL;

            if ($un==NULL || $pw==NULL)
            {
                header("Location: contact.php?err=dataNull");
            }
            else 
            {
                echo '<p>Username is: '.$un.'</p>';
                echo '<p>Password is: '.$pw.'</p>';
                if ($pw=="abc999")
                {
                    echo '<h3>Welcome Admin!</h3>';
                }
            }
            ?>
    </body>
</html>