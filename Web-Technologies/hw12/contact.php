<?php
    include("functions.php");
    $form = processContactForm();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Fabian's Contact Page.</title>
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
                <a href="index.html">Home</a>
                <a href="hobbies.html">Hobbies</a>
                <a href="school.html">School</a>
                <a href="work.html">Work</a>
                <a href="contact.php" class="active" aria-current="page">Contact</a>
            </nav>
        </header>

        <main class="site-main">
            <section class="page-panel hero-panel">
                <div class="content-card">
                    <?php if (!$form['formSubmitted'] || count($form['errors']) > 0): ?>
                        <h2 class="page-title">Fill out the contact form below</h2>
                        <?php if ($form['formSubmitted'] && count($form['errors']) > 0): ?>
                            <div class="alert alert-danger" style="background-color: #fff5f5; border: 1px solid #b23b3b; color: #b23b3b; padding: 12px; margin-bottom: 18px; border-radius: 4px; font-family: 'Josefin Sans', sans-serif; font-weight: 600;">
                                Please correct the errors in the form below before submitting.
                            </div>
                        <?php endif; ?>
                        <form method="post" action="" novalidate>
                            <div class="form-group <?php echo $form['classesByField']['firstName']; ?>" id="firstNameGroup">
                                <label for="firstName">First Name</label>
                                <input type="text" id="firstName" name="firstName" class="form-control" placeholder="Enter first name" value="<?php echo htmlspecialchars($form['fields']['firstName']); ?>">
                                <span class="help-block" id="firstNameFeedback"><?php echo $form['errorsByField']['firstName']; ?></span>
                            </div>

                            <div class="form-group <?php echo $form['classesByField']['lastName']; ?>" id="lastNameGroup">
                                <label for="lastName">Last Name</label>
                                <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Enter last name" value="<?php echo htmlspecialchars($form['fields']['lastName']); ?>">
                                <span class="help-block" id="lastNameFeedback"><?php echo $form['errorsByField']['lastName']; ?></span>
                            </div>

                            <div class="form-group <?php echo $form['classesByField']['email']; ?>" id="emailGroup">
                                <label for="email">Email</label>
                                <input type="text" id="email" name="email" class="form-control" placeholder="username@example.com" value="<?php echo htmlspecialchars($form['fields']['email']); ?>">
                                <span class="help-block" id="emailFeedback"><?php echo $form['errorsByField']['email']; ?></span>
                            </div>

                            <div class="form-group <?php echo $form['classesByField']['phone']; ?>" id="phoneGroup">
                                <label for="phone">Phone Number</label>
                                <input type="text" id="phone" name="phone" class="form-control" placeholder="1234567890" value="<?php echo htmlspecialchars($form['fields']['phone']); ?>">
                                <span class="help-block" id="phoneFeedback"><?php echo $form['errorsByField']['phone']; ?></span>
                            </div>

                            <div class="form-group <?php echo $form['classesByField']['userName']; ?>" id="unGroup">
                                <label class="control-label" for="username">Username</label>
                                <input type="text" class="form-control" id="username" name="userName" placeholder="Choose a username" value="<?php echo htmlspecialchars($form['fields']['userName']); ?>">
                                <span class="help-block" id="unFeedback"><?php echo $form['errorsByField']['userName']; ?></span>
                            </div>

                            <div class="form-group <?php echo $form['classesByField']['passWord']; ?>" id="pwGroup">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="passWord" placeholder="Choose a password" value="<?php echo htmlspecialchars($form['fields']['passWord']); ?>">
                                <span class="help-block" id="pwFeedback"><?php echo $form['errorsByField']['passWord']; ?></span>
                            </div>

                            <div class="form-group <?php echo $form['classesByField']['comments']; ?>" id="commentsGroup">
                                <label for="comments">Comments</label>
                                <textarea id="comments" name="comments" class="form-control" rows="4" placeholder="Write your comments here:"><?php echo htmlspecialchars($form['fields']['comments']); ?></textarea>
                                <span class="help-block" id="commentsFeedback"><?php echo $form['errorsByField']['comments']; ?></span>
                            </div>

                            <button class="btn btn-success form-submit-bt" type="submit" name="submit" value="submit">Submit Message</button>
                        </form>
                    <?php else: ?>
                        <h2 class="page-title">Contact Form Data</h2>
                        <div class="submitted-data">
                            <p><strong>First Name:</strong> <?php echo htmlspecialchars($form['fields']['firstName']); ?></p>
                            <p><strong>Last Name:</strong> <?php echo htmlspecialchars($form['fields']['lastName']); ?></p>
                            <p><strong>Email:</strong> <?php echo htmlspecialchars($form['fields']['email']); ?></p>
                            <p><strong>Phone Number:</strong> <?php echo htmlspecialchars($form['fields']['phone']); ?></p>
                            <p><strong>Username:</strong> <?php echo htmlspecialchars($form['fields']['userName']); ?></p>
                            <p><strong>Password:</strong> <?php echo htmlspecialchars($form['fields']['passWord']); ?></p>
                            <p><strong>Comments:</strong> <?php echo nl2br(htmlspecialchars($form['fields']['comments'])); ?></p>
                        </div>
                    <?php endif; ?>
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