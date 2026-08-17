<?php
    if (session_status() === PHP_SESSION_NONE) {
        session_start();
    }
?>
                    <h2 class="page-title">Fill out the contact form below</h2>
                    <?php
                        if (isset($_GET['err'])) {
                            if ($_GET['err'] == "notSubmitted") {
                                echo '<div class="alert alert-danger">Error: Do not load results.php directly — submit the form.</div>';
                            } else {
                                echo '<div class="alert alert-danger">Error: ' . htmlspecialchars($_GET['err']) . '</div>';
                            }
                        }
                    ?>
                    <form method="post" action="">
                        <div class="form-group" id="firstNameGroup">
                            <label for="firstName">First Name</label>
                            <input type="text" id="firstName" name="firstName" class="form-control" placeholder="Enter first name">
                        </div>
                        <div class="form-group" id="lastNameGroup">
                            <label for="lastName">Last Name</label>
                            <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Enter last name">
                        </div>
                        <div class="form-group" id="emailGroup">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" class="form-control" placeholder="username@example.com">
                        </div>
                        <div class="form-group" id="phoneGroup">
                            <label for="phone">Phone Number</label>
                            <input type="text" id="phone" name="phone" class="form-control" placeholder="1234567890">
                        </div>
                        <div class="form-group" id="unGroup">
                            <label class="control-label" for="username">Create a username:</label>
                            <input type="text" class="form-control" id="username" name="userName" placeholder="Enter Username">
                            <div class="help-block" id="unFeedback"></div>
                        </div>
                        <div class="form-group" id="pwGroup">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="passWord" placeholder="Enter Password">
                            <div class="help-block" id="pwFeedback"></div>
                        </div>
                        <div class="form-group" id="commentsGroup">
                            <label for="comments">Comments</label>
                            <textarea id="comments" name="comments" class="form-control" rows="4" placeholder="Write your comments here:"></textarea>
                        </div>
                        <button class="btn btn-success" type="submit" name="submit" value="submit">Sign Up!</button>
                    </form>

<?php
if (!function_exists('redirect')) {
    function redirect($url) {
        header("Location: " . $url);
        exit;
    }
}

if (isset($_POST['submit'])) {
    $errors="";
    $fn=addslashes($_POST['firstName']);
    if ($fn==NULL)
        $errors.="fnNull";
    if (!ctype_alpha($fn))
        $errors.="fnInvalid";
    $_SESSION['fn']=$fn;
    $ln=addslashes($_POST['lastName']);
    if ($ln==NULL)
        $errors.="lnNull";
    if (!ctype_alpha($ln))
        $errors.="lnInvalid";
    if ($errors!=NULL)
        redirect("index.php?page=contact&err=$errors");
    $un=addslashes($_POST['userName']);
    $pw=addslashes($_POST['passWord']);
    $phone=addslashes($_POST['phone']);
    $email=addslashes($_POST['email']);
    $comments=addslashes($_POST['comments']);

    //create ODBC string
    $dbUser="web_user";
    $dbPw="AEAwlapl/Hqz/*Hv";
    $host="localhost";
    $db="contact_data";
    $dblink=new mysqli($host,$dbUser,$dbPw,$db); //odbc itself
    $sql="Insert into `contact_info` (`first_name`,`last_name`,`email`,`phone`,`user_name`,`pass_word`,`comments`) Values ('$fn','$ln','$email','$phone','$un','$pw','$comments')";
    $dblink->query($sql) or
        die("<h3>Something went wrong with<br>$sql</h3>".$dblink->error);
    echo '<h2>Data successfully sent to database!</h2>';
}
?>