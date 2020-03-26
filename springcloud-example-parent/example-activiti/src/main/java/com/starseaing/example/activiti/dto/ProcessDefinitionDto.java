package com.starseaing.example.activiti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * TODO
 *
 * @author chentc
 * @since 2020/3/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessDefinitionDto {

    /** unique identifier */
    private String id;

    /** category name which is derived from the targetNamespace attribute in the definitions element */
    String category;

    /** label used for display purposes */
    private String name;

    /** unique name for all versions this process definitions */
    private String key;

    /** description of this process **/
    private String description;

    /** version of this process definition */
    private int version;

    /** name of {@link RepositoryService#getResourceAsStream(String, String) the resource}
     * of this process definition. */
    private String resourceName;

    /** The deployment in which this process definition is contained. */
    private String deploymentId;

    /** The resource name in the deployment of the diagram image (if any). */
    private String diagramResourceName;

    /** Does this process definition has a {@link FormService#getStartFormData(String) start form key}. */
    private boolean hasStartFormKey;

    /** Does this process definition has a graphical notation defined (such that a diagram can be generated)? */
    private boolean hasGraphicalNotation;

    /** Returns true if the process definition is in suspended state. */
    private boolean isSuspended;

    /** The tenant identifier of this process definition */
    private String tenantId;

    public static ProcessDefinitionDto toProcessDefinitionDto(ProcessDefinition pd){
        ProcessDefinitionDto dto = ProcessDefinitionDto.builder()
                .category(pd.getCategory())
                .deploymentId(pd.getDeploymentId())
                .description(pd.getDescription())
                .diagramResourceName(pd.getDiagramResourceName())
                .hasGraphicalNotation(pd.hasGraphicalNotation())
                .hasStartFormKey(pd.hasStartFormKey())
                .id(pd.getId())
                .isSuspended(pd.isSuspended())
                .key(pd.getKey())
                .name(pd.getName())
                .resourceName(pd.getResourceName())
                .tenantId(pd.getTenantId())
                .version(pd.getVersion())
                .build();
        return dto;
    }
}
