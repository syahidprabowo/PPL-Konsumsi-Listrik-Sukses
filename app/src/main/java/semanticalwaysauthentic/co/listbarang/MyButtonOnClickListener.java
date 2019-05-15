/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package semanticalwaysauthentic.co.listbarang;;

import android.view.View;

/**
 * Instantiated for the Edit and Delete buttons in WordListAdapter.
 */
public class MyButtonOnClickListener implements View.OnClickListener {
    private static final String TAG = View.OnClickListener.class.getSimpleName();

    int id;
    String word;
    int watt;
    int durasi;
    int jumlah;

    String Swatt;
    String Sdursai;
    String Sjumlah;

    public MyButtonOnClickListener(int id, String word, int watt, int durasi, int jumlah) {
        this.id = id;
        this.word = word;
        this.watt = watt;
        this.durasi = durasi;
        this.jumlah = jumlah;

        this.Swatt = String.valueOf(watt);
        this.Sdursai = String.valueOf(durasi);
        this.Sjumlah = String.valueOf(jumlah);
    }

    public void onClick(View v) {
        // Implemented in WordListAdapter
    }
}
