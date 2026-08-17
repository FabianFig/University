
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
                    <form method="post" action="results.php">
                        <div class="form-group" id="unGroup">
                            <label class="control-label" for="username">Create a username:</label>
                            <input type="text" class="form-control" id="username" name="userName" placeholder="Enter Username">
                            <div class="help-block" id="unFeedback"></div>
                        </div>
                        <div class="form-group" id="firstNameGroup">
                            <label for="firstName">First Name</label>
                            <input type="text" id="firstName" name="firstName" class="form-control" placeholder="Enter first name">
                        </div>
                        <div class="form-group" id="lastNameGroup">
                            <label for="lastName">Last Name</label>
                            <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Enter last name">
                        </div>
                        <div class="form-group" id="pwGroup">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="passWord" placeholder="Enter Password">
                            <div class="help-block" id="pwFeedback"></div>
                        </div>
                        <button class="btn btn-success" type="submit" name="submit" value="submit">Sign Up!</button>
                    </form>