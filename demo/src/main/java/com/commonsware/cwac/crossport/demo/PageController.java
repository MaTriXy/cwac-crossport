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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import com.commonsware.cwac.crossport.demo.R;
import com.commonsware.cwac.crossport.design.widget.TextInputLayout;

class PageController extends RecyclerView.ViewHolder {
  private final TextInputLayout editor;

  PageController(View itemView) {
    super(itemView);

    editor=(TextInputLayout)itemView.findViewById(R.id.editor);
  }

  void setText(String text) {
    editor.getEditText().setText(text);
    editor.setHint(toString());
  }

  String getText() {
    return(editor.getEditText().getText().toString());
  }

  @Override
  public String toString() {
    return(getTitle(editor.getContext(), getAdapterPosition()));
  }

  static String getTitle(Context ctxt, int position) {
    return(ctxt.getString(R.string.hint, position+1));
  }
}
