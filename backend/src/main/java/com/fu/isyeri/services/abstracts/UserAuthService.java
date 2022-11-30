package com.fu.isyeri.services.abstracts;

import com.fu.isyeri.dto.Credentials;
import com.fu.isyeri.result.AuthResult;

public interface UserAuthService {

	AuthResult authenticate(Credentials credentials);
}
