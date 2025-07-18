package com.officemanagement.office.controller;

import com.officemanagement.office.dto.request.ApplyLeaveRequestDTO;
import com.officemanagement.office.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping("/apply")
    public ResponseEntity<String> applyLeave(@RequestBody ApplyLeaveRequestDTO requestDTO) {
        leaveService.applyLeave(requestDTO);
        return ResponseEntity.ok("Leave applied successfully");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editLeave(
            @PathVariable Long id,
            @RequestBody ApplyLeaveRequestDTO dto) {
        leaveService.editLeave(id, dto);
        return ResponseEntity.ok("Leave updated successfully");
    }

    @DeleteMapping("/reject/{id}")
    public ResponseEntity<String> rejectLeave(
            @PathVariable Long id,
            @RequestParam Long approverId,
            @RequestParam String reason) {
        leaveService.rejectLeave(id, approverId, reason);
        return ResponseEntity.ok("Leave rejected successfully");
    }
}
