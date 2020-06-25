package com.example.sook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.appsync.ModelWithMetadata;
import com.amplifyframework.datastore.generated.model.Post;
import com.amplifyframework.datastore.generated.model.PostStatus;
import com.amplifyframework.datastore.generated.model.Priority;
import com.amplifyframework.datastore.generated.model.Todo;
import com.amplifyframework.hub.HubChannel;

import org.w3c.dom.Comment;


public class MainActivity extends AppCompatActivity {
    ImageView waterdrop;
    ImageView moja;
    Animation getstarted1;
    Animation getstarted2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waterdrop = (ImageView) findViewById(R.id.waterdrop);
        getstarted1 = AnimationUtils.loadAnimation(this,R.anim.getstarted1);
        waterdrop.setAnimation(getstarted1);

        moja = (ImageView) findViewById(R.id.moja);
        getstarted2 = AnimationUtils.loadAnimation(this,R.anim.getstarted2);
        moja.setAnimation(getstarted2);

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }
        Amplify.Hub.subscribe(
                HubChannel.DATASTORE,
                event -> DataStoreChannelEventName.RECEIVED_FROM_CLOUD.toString().equals(event.getName()),
                event -> {
                    ModelWithMetadata<?> modelWithMetadata = (ModelWithMetadata<?>) event.getData();
                    Todo todo = (Todo) modelWithMetadata.getModel();

                    Log.i("Tutorial", "==== Todo ====");
                    Log.i("Tutorial", "Name: " + todo.getName());

                    if (todo.getPriority() != null) {
                        Log.i("Tutorial", "Priority: " + todo.getPriority().toString());
                    }

                    if (todo.getDescription() != null) {
                        Log.i("Tutorial", "Description: " + todo.getDescription());
                    }
                }
        );

//DataStore
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin()); // If using remote model synchronization
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
        Post post = Post.builder()
                .title("My First Post comments")
                .rating(10)
                .status(PostStatus.ACTIVE)
                .build();
        //
    }

    public void onButtonClick(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
