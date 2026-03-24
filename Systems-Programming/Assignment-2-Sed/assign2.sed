
s/\b[A-Z]{2}DL ?[0-9]{6,8}\b/DL <redacted>/g;

s/\b(4[0-9-]{11,15})\b/VISA-\1/;
s/\b(5[0-9-]{11,15})\b/MC-\1/;
s/\b(3[47][0-9-]{10,14})\b/AMEX-\1/;
s/\b(6[0-9-]{11,15})\b/DISC-\1/;
s/([A-Z]+-)[0-9-]+([0-9]{4})/\1\2/;

s/\bTX ?[A-Z0-9-]{6,7}\b/TX <redacted>/g;

s/<date>/'"$current_date"'/g;
s/<mname>/City of Brook Haven, Connecticut/g;