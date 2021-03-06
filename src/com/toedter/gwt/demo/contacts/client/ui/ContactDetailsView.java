/*******************************************************************************
 * Copyright (c) 2012 Kai Toedter and others.
 * 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 *     Kai Toedter - initial API and implementation
 ******************************************************************************/

package com.toedter.gwt.demo.contacts.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.toedter.gwt.demo.contacts.shared.Contact;

public class ContactDetailsView extends Composite implements IContactDetailsView {

	private static ContactDetailsViewUiBinder uiBinder = GWT.create(ContactDetailsViewUiBinder.class);
	@UiField
	TextBox title;
	@UiField
	TextBox name;
	@UiField
	TextBox email;
	@UiField
	TextBox company;
	@UiField
	Image image;

	private Contact contact;

	interface ContactDetailsViewUiBinder extends UiBinder<Widget, ContactDetailsView> {
	}

	public ContactDetailsView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setContact(Contact contact) {
		this.contact = contact;
		if (contact == null) {
			return;
		}

		title.setText(contact.getTitle());
		name.setText(contact.getFirstName() + " " + contact.getLastName());
		company.setText(contact.getCompany());
		email.setText(contact.getEmail());

		image.setUrl(contact.getJpegString());
	}

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getContactEmail() {
		return email.getText();
	}

	@Override
	public void clear() {
		contact = null;
		title.setText("");
		name.setText("");
		company.setText("");
		email.setText("");

		image.setUrl("");
	}

	@Override
	public Contact getContact() {
		if (contact == null) {
			contact = new Contact();
		}

		contact.setTitle(title.getText());
		String[] names = name.getText().split(" ");
		if (names.length > 0) {
			contact.setFirstName(names[0]);
		}
		if (names.length > 1) {
			contact.setLastName(names[1]);
		}
		if (names.length > 2) {
			contact.setMiddleName(names[1]);
			contact.setLastName(names[2]);
		}
		contact.setCompany(company.getText());
		contact.setEmail(email.getText());
		return contact;
	}
}
