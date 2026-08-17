<?php

function isNullOrEmpty($value) {
    if ($value === NULL) {
        return true;
    }
    if (trim($value) === "") {
        return true;
    }
    return false;
}

function validateName($name, $label) {
    if (isNullOrEmpty($name)) {
        return $label . " cannot be NULL/empty.";
    }
    if (!preg_match("/^[a-zA-Z'-]+$/", $name)) {
        return $label . " may only contain alphabet characters, hyphens (-), and apostrophes (').";
    }
    return "";
}

function validateEmail($email) {
    if (isNullOrEmpty($email)) {
        return "Email cannot be NULL/empty.";
    }
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        return "Email must be a valid email address format.";
    }
    return "";
}

function validatePhone($phone) {
    if (isNullOrEmpty($phone)) {
        return "Phone number cannot be NULL/empty.";
    }
    if (!preg_match("/^[0-9]+$/", $phone)) {
        return "Phone number may only contain digits.";
    }
    return "";
}

function validateRequired($value, $label) {
    if (isNullOrEmpty($value)) {
        return $label . " cannot be NULL/empty.";
    }
    return "";
}

function processContactForm() {
    $data = array(
        'formSubmitted' => isset($_POST['submit']),
        'errors' => array(),
        'fields' => array(
            'firstName' => '',
            'lastName' => '',
            'email' => '',
            'phone' => '',
            'userName' => '',
            'passWord' => '',
            'comments' => ''
        ),
        'errorsByField' => array(
            'firstName' => '',
            'lastName' => '',
            'email' => '',
            'phone' => '',
            'userName' => '',
            'passWord' => '',
            'comments' => ''
        ),
        'classesByField' => array(
            'firstName' => '',
            'lastName' => '',
            'email' => '',
            'phone' => '',
            'userName' => '',
            'passWord' => '',
            'comments' => ''
        )
    );

    if ($data['formSubmitted']) {
        if (isset($_POST['firstName'])) {
            $data['fields']['firstName'] = trim($_POST['firstName']);
        }
        if (isset($_POST['lastName'])) {
            $data['fields']['lastName'] = trim($_POST['lastName']);
        }
        if (isset($_POST['email'])) {
            $data['fields']['email'] = trim($_POST['email']);
        }
        if (isset($_POST['phone'])) {
            $data['fields']['phone'] = trim($_POST['phone']);
        }
        if (isset($_POST['userName'])) {
            $data['fields']['userName'] = trim($_POST['userName']);
        }
        if (isset($_POST['passWord'])) {
            $data['fields']['passWord'] = trim($_POST['passWord']);
        }
        if (isset($_POST['comments'])) {
            $data['fields']['comments'] = trim($_POST['comments']);
        }

        // validating First Name
        $err = validateName($data['fields']['firstName'], "First name");
        if ($err !== "") {
            $data['errorsByField']['firstName'] = $err;
            $data['classesByField']['firstName'] = "has-error";
            $data['errors'][] = $err;
        }

        // validating Last Name
        $err = validateName($data['fields']['lastName'], "Last name");
        if ($err !== "") {
            $data['errorsByField']['lastName'] = $err;
            $data['classesByField']['lastName'] = "has-error";
            $data['errors'][] = $err;
        }

        // validating Email
        $err = validateEmail($data['fields']['email']);
        if ($err !== "") {
            $data['errorsByField']['email'] = $err;
            $data['classesByField']['email'] = "has-error";
            $data['errors'][] = $err;
        }

        // validating Phone
        $err = validatePhone($data['fields']['phone']);
        if ($err !== "") {
            $data['errorsByField']['phone'] = $err;
            $data['classesByField']['phone'] = "has-error";
            $data['errors'][] = $err;
        }

        // validating Username
        $err = validateRequired($data['fields']['userName'], "Username");
        if ($err !== "") {
            $data['errorsByField']['userName'] = $err;
            $data['classesByField']['userName'] = "has-error";
            $data['errors'][] = $err;
        }

        // validating Password
        $err = validateRequired($data['fields']['passWord'], "Password");
        if ($err !== "") {
            $data['errorsByField']['passWord'] = $err;
            $data['classesByField']['passWord'] = "has-error";
            $data['errors'][] = $err;
        }

        // validating Comments
        $err = validateRequired($data['fields']['comments'], "Comments");
        if ($err !== "") {
            $data['errorsByField']['comments'] = $err;
            $data['classesByField']['comments'] = "has-error";
            $data['errors'][] = $err;
        }
    }

    return $data;
}

?>
