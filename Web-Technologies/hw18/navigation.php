<?php
$dblink=dbConnect("cms");
$sql="SELECT `auto_id`, `location`, `title`, `status`, `position` FROM `menu` WHERE `status`='active' ORDER BY `position` ASC";
$results=$dblink->query($sql) or
        die("Something went wrong with $sql<br>".$dblink->error);
        
while($data=$results->fetch_array(MYSQLI_ASSOC)){
        if($page==$data['location'])
                echo '<a href="index.php?page='.$data['location'].'" class="active" aria-current="page">'.$data['title'].'</a>';
        else
                echo '<a href="index.php?page='.$data['location'].'">'.$data['title'].'</a>';
}
?>