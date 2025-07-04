/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.13.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.tinubu.web.api;

import com.tinubu.web.dto.Policy;
import com.tinubu.web.dto.PolicyInput;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.13.0")
@Validated
@Tag(name = "Default", description = "the Default API")
public interface DefaultApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /policies : Lister toutes les polices d&#39;assurance
     *
     * @return Liste des polices (status code 200)
     */
    @Operation(
        operationId = "policiesGet",
        summary = "Lister toutes les polices d'assurance",
        responses = {
            @ApiResponse(responseCode = "200", description = "Liste des polices", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Policy.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/policies",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<Policy>> policiesGet(
        
    ) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"createdAt\" : \"2024-06-01T12:00:00Z\", \"endDate\" : \"2025-01-01T00:00:00Z\", \"name\" : \"Assurance Habitation\", \"id\" : 1, \"startDate\" : \"2024-01-01T00:00:00Z\", \"status\" : \"ACTIVE\", \"updatedAt\" : \"2024-06-10T08:30:00Z\" }, { \"createdAt\" : \"2024-06-01T12:00:00Z\", \"endDate\" : \"2025-01-01T00:00:00Z\", \"name\" : \"Assurance Habitation\", \"id\" : 1, \"startDate\" : \"2024-01-01T00:00:00Z\", \"status\" : \"ACTIVE\", \"updatedAt\" : \"2024-06-10T08:30:00Z\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /policies/{id} : Obtenir une police par son identifiant
     *
     * @param id  (required)
     * @return Police trouvée (status code 200)
     *         or Police non trouvée (status code 404)
     */
    @Operation(
        operationId = "policiesIdGet",
        summary = "Obtenir une police par son identifiant",
        responses = {
            @ApiResponse(responseCode = "200", description = "Police trouvée", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Policy.class))
            }),
            @ApiResponse(responseCode = "404", description = "Police non trouvée")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/policies/{id}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<Policy> policiesIdGet(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"createdAt\" : \"2024-06-01T12:00:00Z\", \"endDate\" : \"2025-01-01T00:00:00Z\", \"name\" : \"Assurance Habitation\", \"id\" : 1, \"startDate\" : \"2024-01-01T00:00:00Z\", \"status\" : \"ACTIVE\", \"updatedAt\" : \"2024-06-10T08:30:00Z\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /policies/{id} : Mettre à jour une police existante
     *
     * @param id  (required)
     * @param policyInput  (required)
     * @return Police mise à jour (status code 200)
     *         or Police non trouvée (status code 404)
     */
    @Operation(
        operationId = "policiesIdPut",
        summary = "Mettre à jour une police existante",
        responses = {
            @ApiResponse(responseCode = "200", description = "Police mise à jour", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Policy.class))
            }),
            @ApiResponse(responseCode = "404", description = "Police non trouvée")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/policies/{id}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Policy> policiesIdPut(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "PolicyInput", description = "", required = true) @Valid @RequestBody PolicyInput policyInput
    ) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"createdAt\" : \"2024-06-01T12:00:00Z\", \"endDate\" : \"2025-01-01T00:00:00Z\", \"name\" : \"Assurance Habitation\", \"id\" : 1, \"startDate\" : \"2024-01-01T00:00:00Z\", \"status\" : \"ACTIVE\", \"updatedAt\" : \"2024-06-10T08:30:00Z\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /policies : Créer une nouvelle police d&#39;assurance
     *
     * @param policyInput  (required)
     * @return Police créée (status code 201)
     */
    @Operation(
        operationId = "policiesPost",
        summary = "Créer une nouvelle police d'assurance",
        responses = {
            @ApiResponse(responseCode = "201", description = "Police créée", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Policy.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/policies",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Policy> policiesPost(
        @Parameter(name = "PolicyInput", description = "", required = true) @Valid @RequestBody PolicyInput policyInput
    ) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"createdAt\" : \"2024-06-01T12:00:00Z\", \"endDate\" : \"2025-01-01T00:00:00Z\", \"name\" : \"Assurance Habitation\", \"id\" : 1, \"startDate\" : \"2024-01-01T00:00:00Z\", \"status\" : \"ACTIVE\", \"updatedAt\" : \"2024-06-10T08:30:00Z\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
