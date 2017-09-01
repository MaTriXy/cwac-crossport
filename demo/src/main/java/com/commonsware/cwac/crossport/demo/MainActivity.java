/***
 Copyright (c) 2016 CommonsWare, LLC

 Licensed under the Apache License, Version 2.0 (the "License"); you may
 not use this file except in compliance with the License. You may obtain
 a copy of the License at http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.commonsware.cwac.crossport.demo;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.MenuItem;
import android.view.View;
import com.commonsware.cwac.crossport.v7.app.ActionBarDrawerToggle;
import com.commonsware.cwac.crossport.design.widget.Snackbar;
import com.commonsware.cwac.crossport.design.widget.TabLayout;

public class MainActivity extends Activity {
  private static final String STATE_ADAPTER="adapter";
  private static final int PERIOD=5000;
  private PageAdapter adapter;
  private RecyclerView pager;
  private ActionBarDrawerToggle toggle;
  private final SnapHelper snapperCarr=new PagerSnapHelper();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    pager=(RecyclerView)findViewById(R.id.pager);

    final LinearLayoutManager layoutManager=new LinearLayoutManager(this,
      LinearLayoutManager.HORIZONTAL, false);

    pager.setLayoutManager(layoutManager);
    snapperCarr.attachToRecyclerView(pager);

    adapter=new PageAdapter(pager, getLayoutInflater());
    pager.setAdapter(adapter);

    final TabLayout tabs=(TabLayout)findViewById(R.id.tabs);

    for (int i=0;i<adapter.getItemCount();i++) {
      tabs.addTab(tabs.newTab().setText(adapter.getTabText(this, i)));
    }

    tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        pager.smoothScrollToPosition(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {
        // unused
      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {
        // unused
      }
    });

    pager.setOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int tab=layoutManager.findFirstCompletelyVisibleItemPosition();

        if (tab>=0 && tab<tabs.getTabCount()) {
          tabs.getTabAt(tab).select();
        }
      }
    });

    pager.postDelayed(periodic, PERIOD);

    DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer);

    toggle=
      new ActionBarDrawerToggle(this, drawer,
        R.string.drawer_toggle_open, R.string.drawer_toggle_close);
    drawer.setDrawerListener(toggle);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setHomeButtonEnabled(true);
  }

  @Override
  protected void onSaveInstanceState(Bundle state) {
    super.onSaveInstanceState(state);

    Bundle adapterState=new Bundle();

    adapter.onSaveInstanceState(adapterState);
    state.putBundle(STATE_ADAPTER, adapterState);
  }

  @Override
  protected void onRestoreInstanceState(Bundle state) {
    super.onRestoreInstanceState(state);

    adapter.onRestoreInstanceState(state.getBundle(STATE_ADAPTER));
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    toggle.onConfigurationChanged(newConfig);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return(toggle.onOptionsItemSelected(item));
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    toggle.syncState();
  }

  private Runnable periodic=new Runnable() {
    @Override
    public void run() {
      pager.postDelayed(periodic, 5000);
      Snackbar
        .make(pager, R.string.hello, Snackbar.LENGTH_LONG)
        .setAction(R.string.stop, new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            pager.removeCallbacks(periodic);
          }
        })
        .show();
    }
  };
}