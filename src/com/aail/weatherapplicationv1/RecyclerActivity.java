package com.aail.weatherapplicationv1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aail.weatherapplicationv1.MainActivity;

public class RecyclerActivity extends MainActivity implements RecyclerViewAdapter.OnItemClickListener{

	 	private RecyclerView myRecyclerView;
	    private LinearLayoutManager linearLayoutManager;
	    private RecyclerViewAdapter myRecyclerViewAdapter;
	    RecyclerView.LayoutManager mLayoutManager;
	    EditText nameField;
	    Button btnAdd;
	    
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.recycler_activity);
	        myRecyclerView = (RecyclerView)findViewById(R.id.myrecyclerview);
	        myRecyclerView.setHasFixedSize(true);
	        
	        
	        myRecyclerViewAdapter = new RecyclerViewAdapter(this);
	        myRecyclerViewAdapter.setOnItemClickListener(this);
	        myRecyclerView.setAdapter(myRecyclerViewAdapter);
	        
	        
	        mLayoutManager = new GridLayoutManager(this, 2);
	        myRecyclerView.setLayoutManager(mLayoutManager);
	        
	        nameField = (EditText)findViewById(R.id.namefield);
	        nameField.setText(location_name);
	        btnAdd = (Button)findViewById(R.id.addbutton);
	        btnAdd.setOnClickListener(new View.OnClickListener(){
	            @Override
	            public void onClick(View v) {
	                String newName =nameField.getText().toString();
	                            	              
	                if(!newName.equals("")){
	                    if(myRecyclerViewAdapter.getItemCount()>1){
	                    	myRecyclerViewAdapter.add(1, newName);
	                    	nameField.setText("");
	                      }else
	                      {
	                    	myRecyclerViewAdapter.add(0, newName);
	                    	nameField.setText("");
	                      }
	                }
	            }
	        });
	    }
	    
	    @Override
	    public void onItemClick(RecyclerViewAdapter.ItemHolder item, int position) {
	        Toast.makeText(this, "position = " + item.getItemName(),
					Toast.LENGTH_SHORT).show();
	       
	    }
	    
	   
	}