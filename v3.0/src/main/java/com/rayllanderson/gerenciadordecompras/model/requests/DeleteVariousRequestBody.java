package com.rayllanderson.gerenciadordecompras.model.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteVariousRequestBody {
    private Long id;
}
