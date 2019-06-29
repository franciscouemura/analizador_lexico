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
	sub esp,8
	push 2
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 4], ebx
	push 1
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 8], ebx
	push dword [ebp - 4]
	push 0
	pop eax
	cmp dword [esp], eax
	jl rotuloFalse1
	mov dword [esp], 1
	jmp rotuloFim0
rotuloFalse1:
	mov dword [esp], 0
rotuloFim0:
	cmp dword [esp],0
	je rotuloInElse2
	push dword [ebp - 4]
	push dword [ebp - 4]
	pop eax
	imul eax, dword [esp]
	mov dword [esp], eax
	mov eax, dword[@DSP+0]
	pop ebx
	mov dword[eax - 8], ebx
	jmp rotuloEndIf3
rotuloInElse2:
	jmp rotuloEndIf3
rotuloEndIf3:
	push dword [ebp - 8]
	push dword @INTEGER
	call _printf
	add esp, 8
	add esp, 8
	mov ebp, esp
	pop dword[@DSP+0]
	pop ebp
	ret
section .data
	@DSP times 4 db 0
	@INTEGER: db '%d' , 0
