package com.example.student.android13;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mListView;
    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<Room, RoomViewHolder> mFirebaseAdapter;
    private EditText mEditTextName;
    private EditText mEditTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.recView);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        mEditTextName = findViewById(R.id.editTextMessage);
        mEditTextDescription = findViewById(R.id.editTextDescription);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Room room = new Room(mEditTextDescription.getText().toString());

                mDatabaseReference.child("rooms/" + mEditTextName.getText().toString()).setValue(room);

                mEditTextName.setText(null);
                mEditTextDescription.setText(null);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mFirebaseAdapter == null) {
            init();
        }

        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mFirebaseAdapter != null) {
            mFirebaseAdapter.stopListening();
        }
    }

    private void init() {
        Query query = mDatabaseReference.child("rooms").orderByKey();

        FirebaseRecyclerOptions<Room> options = new FirebaseRecyclerOptions.Builder<Room>()
                .setQuery(query, Room.class).build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Room, RoomViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RoomViewHolder holder, int position, @NonNull Room model) {
                holder.mTextViewDescription.setText(model.description);

                final String key = mFirebaseAdapter.getRef(position).getKey();
                holder.mTextViewName.setText(key);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Activity2.class);
                        intent.putExtra("key", key);
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(MainActivity.this)
                        .inflate(android.R.layout.simple_list_item_2, parent, false);
                return new RoomViewHolder(view);
            }
        };

        mListView.setAdapter(mFirebaseAdapter);
    }
}
