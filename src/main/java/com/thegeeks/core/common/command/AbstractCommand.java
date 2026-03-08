package com.thegeeks.core.common.command;

import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.domain.Sort.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableSet;
import com.thegeeks.core.common.command.validation.ValidationConstants;
import com.thegeeks.core.common.command.validation.ValidationGroup;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 5.
 *
 */
@Getter
@Setter
public abstract class AbstractCommand {

	private static final ImmutableSet<String> ELIGIBLE_SORT_PROPERTIES = ImmutableSet.of("id");

	@NotNull(groups = {ValidationGroup.SelectById.class, ValidationGroup.Update.class,
			ValidationGroup.Delete.class}, message = ValidationConstants.ErrorCode.REQUIRED)
	@Min(value = ValidationConstants.MIN_ID, groups = {ValidationGroup.SelectById.class,
			ValidationGroup.Update.class, ValidationGroup.Delete.class},
			message = ValidationConstants.ErrorCode.INVALID)
	private Long id;

	@NotNull(groups = {ValidationGroup.Paging.class},
			message = ValidationConstants.ErrorCode.REQUIRED)
	@Min(value = 0, groups = {ValidationGroup.Paging.class},
			message = ValidationConstants.ErrorCode.INVALID_MIN)
	private Integer page;

	@NotNull(groups = {ValidationGroup.Paging.class},
			message = ValidationConstants.ErrorCode.REQUIRED)
	@Min(value = 2, groups = {ValidationGroup.Paging.class},
			message = ValidationConstants.ErrorCode.INVALID_MIN)
	private Integer size;

	private Integer status;
	private ZonedDateTime created;

	private final ZonedDateTime requestDate;
	private HttpServletRequest request;

	@JsonIgnore
	private final List<Order> orders = List.of(Order.desc("id"));

	/**
	 * Default constructor
	 */
	public AbstractCommand() {
		this.requestDate = ZonedDateTime.now();
	}

	/**
	 *
	 * @param sortBy
	 * @return
	 */
	public boolean isEligibleSortProperties(@Nonnull final String sortBy) {
		return AbstractCommand.ELIGIBLE_SORT_PROPERTIES.contains(sortBy);
	}

}
