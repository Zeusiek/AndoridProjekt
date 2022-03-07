package targonski.com.sklep.messages;

import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import targonski.com.sklep.R;

public final class SendMessage {
    private SendMessage(){};
    public static void sendMail(Context context, String text){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.order));
        i.putExtra(Intent.EXTRA_TEXT, text);
        try {
            context.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
    public static void sendSMS(String txt){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+48100100100", null, txt, null, null);
    }
}
