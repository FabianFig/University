<?php

function redirect($url)
    {?>
        <script type="text/javascript">
            document.location.href="<?php echo $url;?>";
        </script>
<?php
    die();
    }


function dbConnect($db) {
    $dbUser="web_user";
    $dbPw="AEAwlapl/Hqz/*Hv";
    $host="localhost";
    //$db="contact_data";
    return $dblink=new mysqli($host,$dbUser,$dbPw,$db); //odbc itself
}

?>