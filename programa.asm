global _main
	extern _printf
	extern _putchar
	extern _scanf
section .text
_main:
	push ebp
	push dword[@DSP + 0]
	mov ebp,esp
	mov dword[@DSP +0],ebp
	sub esp,12
	push 1
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 4], ebx
	push 2
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 8], ebx
	push dword [ebp - 4]
	push dword [ebp - 8]
	pop eax
	cmp dword [esp], eax
	jl rotuloFalse1
	mov dword [esp], 1
	jmp rotuloFim0
rotuloFalse1:
	mov dword [esp], 0
rotuloFim0:
	cmp dword [esp + 4], 0
	je rotuloFalse2
	cmp dword [esp], 0
	je rotuloFalse2
	mov dword [esp + 4], 1
	jmp rotuloFim3
rotuloFalse2:
	mov dword [esp + 4], 0
rotuloFim3:
	add esp, 4
	cmp dword [esp + 4], 1
	je rotulo_true4
	cmp dword [esp], 1
	je rotulo_true4
	mov dword [esp + 4], 0
	jmp rotuloFim5
rotulo_true4:
	mov dword [esp + 4], 1
rotuloFim5:
	add esp, 4
	cmp dword [esp],0
	je rotuloInElse6
	push 1
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 12], ebx
	jmp rotuloEndIf7
rotuloInElse6:
	push 0
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 12], ebx
rotuloEndIf7:
	push dword [ebp - 12]
	push dword @INTEGER
	call _printf
	add esp, 8
	add esp, 12
	mov esp,ebp
	pop dword[@DSP+8]
	pop ebp
	ret
section .data
	@DSP times 4 db 0
	@INTEGER: db '%d' , 0
