import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [ReactiveFormsModule,
            CommonModule,
            ButtonModule,
            InputTextModule,
            InputNumberModule,
            InputTextareaModule,],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent 
{

   contactForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.contactForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      message: ['', [Validators.required, Validators.maxLength(300)]]
    });
  }

  onSubmit(): void {
    if (this.contactForm.valid) {
      Swal.fire({
        icon: 'success',
        title: 'Message envoyé',
        text: 'Demande de contact envoyée avec succès',
        timer: 2000,
        showConfirmButton: false
      });

      this.contactForm.reset();
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: 'Veuillez remplir correctement tous les champs.',
        timer: 2000,
        showConfirmButton: false
      });
    }
  }
}
