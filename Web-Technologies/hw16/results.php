<!DOCTYPE html>
<html lang="en">
<script src="assets/js/jquery-3.5.1.js"></script>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Contact Form Results</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body>
    <div class="panel panel-default">
        <div class="panel-heading">Database Entries</div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>First Name</th><th>Last Name</th><th>Email</th><th>Phone</th><th>Username</th><th>Password</th><th>Comments</th>
                        </tr>
                    </thead>
                    <tbody id="results">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
<script>
    function refresh_data(){
        $.ajax({
            type: 'get',
            url: 'query_contacts.php',
            success: function(data) {
                $('#results').html(data);
            }
        });
    }
    refresh_data();
    setInterval(function(){refresh_data()},5000);
</script>