package edu.asu.msse.sbhard18.placelibrary.ui.listeners;
/*
 * Copyright 2020  Shwetank Bhardwaj,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Shwetank Bhardwaj, sbhard18@asu.edu
 * Software Engineering, CIDSE, ASU Poly
 *
 * @version February 2020
 */

import edu.asu.msse.sbhard18.placelibrary.model.PlaceDescription;

public interface RecyclerViewEventListener {

    void onItemLongClickListener(PlaceDescription placeDescription);

    void onItemClickListener(PlaceDescription placeDescription);
}
