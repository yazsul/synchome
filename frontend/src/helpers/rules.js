import { defineRule } from "vee-validate"

defineRule("required", (value, [], context) => {
    // HINT: id requirement error related to value of 0
    if (typeof value === "number") {
        return true;
    }
    return !!value || `${context.field} is required`;
});

defineRule("email", (value) => {
    return (
        !value ||
        /[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/.test(
            value
        ) ||
        //TODO: add i18n plugin
        //   i18n.global.t("invalid_email")
        "invalid email"
    );
});

defineRule("password", (value) => {
    // return !value || value.length >= 8 || i18n.global.t("password_too_short");
    return !value || value.length >= 8 || "password too short"
});

defineRule("confirmed", (value, [target]) => {
    if (value === target) {
        return true;
    }
    return 'Passwords must match';
});

defineRule("number", (value) => {
    //TODO: add i18n plugin
    return !value || /^[0-9]+$/.test(value) || "Numbers Only";
});

defineRule("ethiopian_phone_number", (value) => {
    return !value || /^(7|9)\d{8}$/.test(value) || "Invalid Phone";
});
