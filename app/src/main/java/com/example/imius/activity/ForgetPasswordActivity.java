package com.example.imius.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.ActivityForgetPasswordBinding;

import com.example.imius.R;
import com.example.imius.fragment.ChangePasswordFragment;
import com.example.imius.fragment.ForgetPasswordFragment;
import com.example.imius.viewmodel.UserViewModel;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityForgetPasswordBinding binding;
    private UserViewModel viewModel;
    private int code;

    private boolean check = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.activityForgetPasswordEtEmail.setText(DataLocalManager.getEmail());

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        init();
    }

    public void init(){
        binding.activityForgetPasswordCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.activityForgetPasswordBtnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check){
                    Random random = new Random();
                    code = random.nextInt(8999)+1000;

                    String stringHost = "smtp.gmail.com";

                    Properties properties = System.getProperties();
                    properties.put("mail.smtp.host", stringHost);
                    properties.put("mail.smtp.port", "465");
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.auth", "true");

                    javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("imiusg@gmail.com",
                                    getResources().getString(R.string.password_email_server));
                        }
                    });

                    MimeMessage mimeMessage = new MimeMessage(session);

                    try {
                        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
                                binding.activityForgetPasswordEtEmail.getText().toString().trim()));

                        mimeMessage.setSubject("IMIUS CODE VERIFICATION");
                        mimeMessage.setText("Hello, "+ "\n\n" +
                                "iMius sent you an OTP for email verification: " +  code + "\n\n" +
                                "Thank you!");

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //Transport.send(mimeMessage);
                                    javax.mail.Transport.send(mimeMessage);
                                } catch (MessagingException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });

                        thread.start();
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


        binding.activityForgetPasswordBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callForgetPasswordFragment();
            }
        });
    }

    public void callForgetPasswordFragment() {
        ForgetPasswordFragment forgetPasswordFragment = new ForgetPasswordFragment();
        binding.activityForgetPasswordLinearLayout.setVisibility(View.GONE);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_forget_password_frame_content, forgetPasswordFragment);
        transaction.commit();

    }

    @Override
    public void onClick(View view) {

    }
}