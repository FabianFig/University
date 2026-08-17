<?php
switch ($page) {
        case 'hobbies':
                echo '<a href="index.php">Home</a>
                <a href="index.php?page=hobbies" class="active" aria-current="page">Hobbies</a>
                <a href="index.php?page=school">School</a>
                <a href="index.php?page=work">Work</a>
                <a href="index.php?page=contact">Contact</a>';
                break;

        case 'school':
                echo '<a href="index.php">Home</a>
                <a href="index.php?page=hobbies">Hobbies</a>
                <a href="index.php?page=school" class="active" aria-current="page">School</a>
                <a href="index.php?page=work">Work</a>
                <a href="index.php?page=contact">Contact</a>';
                break;

        case 'work':
                echo '<a href="index.php">Home</a>
                <a href="index.php?page=hobbies">Hobbies</a>
                <a href="index.php?page=school">School</a>
                <a href="index.php?page=work" class="active" aria-current="page">Work</a>
                <a href="index.php?page=contact">Contact</a>';
                break;

        case 'contact':
                echo '<a href="index.php">Home</a>
                <a href="index.php?page=hobbies">Hobbies</a>
                <a href="index.php?page=school">School</a>
                <a href="index.php?page=work">Work</a>
                <a href="index.php?page=contact" class="active" aria-current="page">Contact</a>';
                break;

        default:
                echo '<a href="index.php" class="active" aria-current="page">Home</a>
                <a href="index.php?page=hobbies">Hobbies</a>
                <a href="index.php?page=school">School</a>
                <a href="index.php?page=work">Work</a>
                <a href="index.php?page=contact">Contact</a>';
                break;
}
?>