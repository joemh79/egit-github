/*******************************************************************************
 *  Copyright (c) 2011 GitHub Inc.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *    Kevin Sawicki (GitHub Inc.) - initial API and implementation
 *******************************************************************************/
package org.eclipse.egit.github.core.tests.live;

import java.io.IOException;
import java.net.URL;

import junit.framework.TestCase;

import org.apache.http.HttpHost;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;

/**
 * Base live test class.
 */
public abstract class LiveTest extends TestCase {

	/**
	 * Configured client
	 */
	protected GitHubClient client;

	/**
	 * Writable repository
	 */
	protected String writableRepo;

	/**
	 * Configure client
	 * 
	 * @param client
	 * @return specified client
	 */
	protected GitHubClient configure(GitHubClient client) {
		String user = System.getProperty("github.test.user");
		String password = System.getProperty("github.test.password");
		writableRepo = System.getProperty("github.test.repository");
		client.setCredentials(user, password);
		return client;
	}

	/**
	 * Create client for url
	 * 
	 * @param url
	 * @return client
	 * @throws IOException
	 */
	protected GitHubClient createClient(String url) throws IOException {
		GitHubClient client = null;
		if (url != null) {
			URL parsed = new URL(url);
			HttpHost httpHost = new HttpHost(parsed.getHost(),
					parsed.getPort(), parsed.getProtocol());
			client = new GitHubClient(httpHost);
		} else
			client = new GitHubClient();
		return configure(client);
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() throws Exception {
		String testUrl = System.getProperty("github.test.url");
		this.client = createClient(testUrl);
	}
}
