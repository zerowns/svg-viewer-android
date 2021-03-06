package biz.codefuture.svgviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import biz.codefuture.svgviewer.dummy.DummyContent;

/**
 * A fragment representing a single Document detail screen. This fragment is
 * either contained in a {@link DocumentListActivity} in two-pane mode (on
 * tablets) or a {@link DocumentDetailActivity} on handsets.
 */
public class DocumentDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public DocumentDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_document_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			WebView wv = (WebView) rootView.findViewById(R.id.document_detail);
			wv.loadUrl(mItem.content);
			wv.getSettings().setBuiltInZoomControls(true);
	    	wv.getSettings().setDisplayZoomControls(false);
	    	wv.getSettings().setUseWideViewPort(true);
	    	wv.setInitialScale(2);
		}

		return rootView;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.document_fragment, menu);
	}


	
}
