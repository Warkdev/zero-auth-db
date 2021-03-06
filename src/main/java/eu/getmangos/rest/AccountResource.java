package eu.getmangos.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import eu.getmangos.dto.AccountDTO;
import eu.getmangos.dto.srp.RegistrationDTO;

public interface AccountResource {

    @GET
    @Path("v1/account/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves an account given the email",
        description = "This API is retrieving the account with the given from the database."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The account", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = AccountDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Account not found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    @Tag(name = "account")
    public Response findAccount(@PathParam("email") String email);

    @GET
    @Path("v1/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieves a list of accounts matching the search query",
        description = "This API is retrieving the accounts matching the given search query from the database. A missing search query will return all records given the pagination."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "A list of matching accounts", content = @Content(
                        mediaType = "application/json", schema = @Schema(implementation = AccountDTO.class)
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "No result found"),
            @APIResponse(responseCode = "500", description = "An unexpected event occured")
        }
    )
    @Tag(name = "account")
    public Response findBy(@DefaultValue("1") @QueryParam("page") Integer page, @DefaultValue("100") @QueryParam("page_size") Integer pageSize);

    @POST
    @Path("v1/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Register a new account",
        description = "This API is registering a new account based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "201", description = "The account has been registered", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "account")
    public Response register(RegistrationDTO account);

    @PUT
    @Path("v1/account/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit an account",
        description = "This API is updating an existing account based on the provided input."
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "200", description = "The account has been updated", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Account not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "account")
    public Response editAccount(@PathParam("email") String email, AccountDTO entity);

    @DELETE
    @Path("v1/account/{email}")
    @Operation(summary = "Delete an account",
        description = "This API is deleting an existing account based on the provided email."
                + "It will also delete the bans for this account, the link with the realms and the warden logs"
    )
    @APIResponses(
        value = {
            @APIResponse(responseCode = "204", description = "The account has been deleted", content = @Content(
                        mediaType = "application/json"
                )
            ),
            @APIResponse(responseCode = "400", description = "Error with the request"),
            @APIResponse(responseCode = "404", description = "Account not found"),
            @APIResponse(responseCode = "500", description = "An unexpected even occured")
        }
    )
    @Tag(name = "account")
    public Response deleteAccount(@PathParam("email") String email);
}
