<?php

if (!function_exists('redirect')) {
    function redirect($url)
    {?>
        <script type="text/javascript">
            document.location.href="<?php echo $url;?>";
        </script>
<?php
        die();
    }
}

if (!function_exists('dbConnect')) {
    function dbConnect($db) {
        $dbUser="web_user";
        $dbPw="AEAwlapl/Hqz/*Hv";
        $host="localhost";
        return $dblink=new mysqli($host,$dbUser,$dbPw,$db);
    }
}

?>