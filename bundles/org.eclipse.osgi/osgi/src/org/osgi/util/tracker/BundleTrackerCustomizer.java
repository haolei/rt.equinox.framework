/*
 * $Date: 2007-10-03 16:52:14 -0400 (Wed, 03 Oct 2007) $
 * 
 * Copyright (c) OSGi Alliance (2007). All Rights Reserved.
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

package org.osgi.util.tracker;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;

/**
 * The <code>BundleTrackerCustomizer</code> interface allows a
 * <code>BundleTracker</code> object to customize the bundle objects that are
 * tracked. The <code>BundleTrackerCustomizer</code> object is called when a
 * bundle is being added to the <code>BundleTracker</code> object. The
 * <code>BundleTrackerCustomizer</code> can then return an object for the
 * tracked bundle. The <code>BundleTrackerCustomizer</code> object is also
 * called when a tracked bundle has been removed from the
 * <code>BundleTracker</code> object.
 * 
 * <p>
 * The methods in this interface may be called as the result of a
 * <code>BundleEvent</code> being received by a <code>BundleTracker</code>
 * object. Since <code>BundleEvent</code>s are received synchronously by the
 * <code>BundleTracker</code>, it is highly recommended that implementations
 * of these methods do not alter bundle states while being synchronized on any
 * object.
 * 
 * <p>
 * The <code>BundleTracker</code> class is thread-safe. It does not call a
 * <code>BundleTrackerCustomizer</code> object while holding any locks.
 * <code>BundleTrackerCustomizer</code> implementations must also be
 * thread-safe.
 * 
 * @ThreadSafe
 * @version $Revision: 4998 $
 * @since 1.4
 */
public interface BundleTrackerCustomizer {
	/**
	 * A bundle is being added to the <code>BundleTracker</code> object.
	 * 
	 * <p>
	 * This method is called before a bundle which matched the search parameters
	 * of the <code>BundleTracker</code> object is added to it. This method
	 * should return the object to be tracked for this <code>Bundle</code>
	 * object. The returned object is stored in the <code>BundleTracker</code>
	 * object and is available from the
	 * {@link BundleTracker#getBundles() getBundles} method.
	 * 
	 * @param bundle Bundle being added to the <code>BundleTracker</code>
	 *        object.
	 * @param event The bundle event which caused this customizer method to be
	 *        called or <code>null</code> if there is no bundle event
	 *        associated with the call to this method.
	 * @return The object to be tracked for the <code>Bundle</code> object or
	 *         <code>null</code> if the <code>Bundle</code> object should
	 *         not be tracked.
	 */
	public abstract Object addingBundle(Bundle bundle, BundleEvent event);

	/**
	 * A bundle tracked by the <code>BundleTracker</code> object has been
	 * modified.
	 * 
	 * <p>
	 * This method is called when a bundle being tracked by the
	 * <code>BundleTracker</code> object has had its state modified.
	 * 
	 * @param bundle Bundle whose state has been modified.
	 * @param event The bundle event which caused this customizer method to be
	 *        called or <code>null</code> if there is no bundle event
	 *        associated with the call to this method.
	 * @param object The tracked object for the modified bundle.
	 */
	public abstract void modifiedBundle(Bundle bundle, BundleEvent event,
			Object object);

	/**
	 * A bundle tracked by the <code>BundleTracker</code> object has been
	 * removed.
	 * 
	 * <p>
	 * This method is called after a bundle is no longer being tracked by the
	 * <code>BundleTracker</code> object.
	 * 
	 * @param bundle Bundle that has been removed.
	 * @param event The bundle event which caused this customizer method to be
	 *        called or <code>null</code> if there is no bundle event
	 *        associated with the call to this method.
	 * @param object The tracked object for the removed bundle.
	 */
	public abstract void removedBundle(Bundle bundle, BundleEvent event,
			Object object);
}