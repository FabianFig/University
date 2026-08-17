<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Results</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="assets/css/styles.css">
</head>
<body>
    <?php
        include("functions.php");
        if (!isset($_GET['sid']) || $_GET['sid']==NULL){
            redirect("login.php?err=missingSid");
        }
        else{
            $sid=addslashes($_GET['sid']);
            $dblink=dbConnect("contact_data");
            $sql="Select * from `accounts` where `session_id`='$sid'";
            $result=$dblink->query($sql) or
                die("Something went wrong with $sql");
            if ($result->num_rows <= 0){
                redirect("login.php?err=invalidSid");
            }
            else{
                echo '<h2>Welcome to the secure site!</h2>';
                echo '<div class="panel panel-default">';
                echo '<div class="panel-heading">Database Entries</div>';
                echo '<div class="panel-body">';
                echo '<div class="table-responsive">';
                echo '<table class="table table-striped">';
                echo '<thead><tr><th>First Name</th><th>Last Name</th><th>Email</th><th>Phone</th><th>Username</th><th>Password</th><th>Comments</th></tr></thead>';
                echo '<tbody>';
                $sql="Select * from `contact_info`";
                $result=$dblink->query($sql) or
                    die("<h2>Something went wrong with: $sql</h2>".$dblink->error);

                while($data=$result->fetch_array(MYSQLI_ASSOC)){
                    echo '<tr>';
                    echo '<td>'.$data['first_name'].'</td>';
                    echo '<td>'.$data['last_name'].'</td>';
                    echo '<td>'.$data['email'].'</td>';
                    echo '<td>'.$data['phone'].'</td>';
                    echo '<td>'.$data['user_name'].'</td>';
                    echo '<td>'.$data['pass_word'].'</td>';
                    echo '<td>'.$data['comments'].'</td>';
                    echo '</tr>';
                }
                echo '</tbody>';
                echo '</table>';
                echo '</div>';
                echo '</div>';
                echo '</div>';
            }
        }
    ?>
</body>
</html>
