package com.iktpreobuka.egradebook.services.email;

import com.iktpreobuka.egradebook.dto.EmailObjectDTO;

public interface EmailService {

	public void sendTemplateMessage(EmailObjectDTO object) throws Exception;

}
