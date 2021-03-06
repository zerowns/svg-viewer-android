package biz.codefuture.svgviewer;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * An activity representing a list of Documents. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link DocumentDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link DocumentListFragment} and the item details (if present) is a
 * {@link DocumentDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link DocumentListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class DocumentListActivity extends FragmentActivity implements
		DocumentListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_document_list);

		if (findViewById(R.id.document_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((DocumentListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.document_list))
					.setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_zoom_fit:
			return true;
		case R.id.about:
			startActivity(new Intent(DocumentListActivity.this, AboutActivity.class));
		case R.id.document_properties:
			DialogFragment docProps = new DocumentPropertiesDialog();
			docProps.show(getFragmentManager(), "test");
//		case R.id.menu_search:
//			return true; // get json and display thumbnails
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Callback method from {@link DocumentListFragment.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(DocumentDetailFragment.ARG_ITEM_ID, id);
			DocumentDetailFragment fragment = new DocumentDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.document_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, DocumentDetailActivity.class);
			detailIntent.putExtra(DocumentDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
	
	@Override
	public void onStart() {
	    super.onStart();
	    //... // The rest of your onStart() code.
	    EasyTracker.getInstance().activityStart(this); // Add this method.
    }

	@Override
	public void onStop() {
	  super.onStop();
	    //... // The rest of your onStop() code.
	    EasyTracker.getInstance().activityStop(this); // Add this method.
	}	
	
}
