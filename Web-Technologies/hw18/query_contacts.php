<?php
                        include("functions.php");
                        $dblink=dbConnect("contact_data");
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
                    ?>