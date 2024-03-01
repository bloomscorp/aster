package com.bloomscorp.aster.restful;

import com.bloomscorp.aster.nverse.service.AsterAuthorityService;
import com.bloomscorp.aster.support.ResponseParameter;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.raintree.RainTree;
import com.bloomscorp.raintree.restful.RainResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@ConditionalOnMissingBean
public class AuthorityTokenResponse<R extends AsterUserRole<?>> extends RainResponse<R> {

	public AuthorityTokenResponse(RainTree rainTree) {
		super(rainTree);
	}

	@Override
	public String buildEntity(R entity) {
		throw new UnsupportedOperationException("Preparing UserRole response is not supported");
	}

	@Override
	public String buildList(@NotNull List<R> roles) {

		HashMap<String, Object> parameters = new HashMap<>();

		parameters.put(
			ResponseParameter.AUTHORITY,
			AsterAuthorityService.prepareAuthority(roles)
		);

		return this.getRainTree().renderParameterizedSuccessResponse(parameters);
	}
}
