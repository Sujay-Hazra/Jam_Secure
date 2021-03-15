package com.example.jamsecure;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

import androidx.appcompat.app.AppCompatActivity;

public class HelpBot extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ConversationService myConversationService = null;
    private TextView chatDisplayTV;
    private EditText userStatementET;
    private final String IBM_USERNAME = "apikey";
    private final String IBM_PASSWORD = "6LUrcdIljzSlKBzmKXaAxACIkULE4oblQKeZBlMZPajK";
    private final String IBM_WORKSPACE_ID ="6074bb6e-d06c-4d21-a788-d089d0f71b2a";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_bot);
        chatDisplayTV = findViewById(R.id.tv_chat_display);
        userStatementET = findViewById(R.id.et_user_statement);

        //instantiating IBM Watson Conversation Service
        myConversationService =
                new ConversationService(
                        "2020-09-2",
                        IBM_USERNAME,
                        IBM_PASSWORD);
        userStatementET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView tv, int action, KeyEvent keyEvent) {
                if (action == EditorInfo.IME_ACTION_DONE) {
                    //show the user statement
                    final String userStatement = userStatementET.getText().toString();
                    chatDisplayTV.append(
                            Html.fromHtml("<p><b>YOU:</b> " + userStatement + "</p>")
                    );
                    userStatementET.setText("");
                    MessageRequest request = new MessageRequest.Builder()
                            .inputText(userStatement)
                            .build();
                    myConversationService
                            .message(IBM_WORKSPACE_ID, request)
                            .enqueue(new ServiceCallback<MessageResponse>() {
                                @Override
                                public void onResponse(MessageResponse response) {
                                    final String botStatement = response.getText().get(0);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            chatDisplayTV.append(
                                                    Html.fromHtml("<p><b>BOT:</b> " +
                                                            botStatement + "</p>"));
                                        }
                                    });
                                }/*
if (response.getIntents().get(0).getIntent().endsWith("Joke")) {
                                    final Map<String, String> params = new HashMap<String, String>() {{
                                        put("Accept", "text/plain"); }};
                                    Fuel.get("https://icanhazdadjoke.com/").header(params)
                                            .responseString(new Handler<String>() {
                                                @Override
                                                public void success(Request request, Response response, String body) {
                                                    Log.d(TAG, "" + response + " ; " + body);
                                                    chatDisplayTV.append(
                                                            Html.fromHtml("<p><b>BOT:</b> " +
                                                                    body + "</p>")); }
                                                @Override
                                                public void failure(Request request, Response response, FuelError fuelError) {
                                                }}); } }*/

                                @Override
                                public void onFailure(Exception e) {
                                    Log.d(TAG, e.getMessage());
                                }}); }
                return false; }}); }}