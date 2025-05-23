import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CartService } from 'app/products/data-access/cart.service';
import { ButtonModule } from 'primeng/button';
import { InputNumberModule } from 'primeng/inputnumber';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule,TableModule,FormsModule,ButtonModule,InputNumberModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent implements OnInit {

  cartItems: any[] = [];
  total: number = 0;

  constructor(private cartService:CartService)
  {
  }

  ngOnInit(): void 
  {
    const storedCart = localStorage.getItem('cart');
    this.cartItems = storedCart ? JSON.parse(storedCart) : [];
    this.calculateTotal();
  }

  calculateTotal(): void 
  {
    this.total = this.cartItems.reduce((acc, item) => acc + item.price * item.quantity, 0);
  }

  removeItem(productId: number): void 
  {
    this.cartItems = this.cartItems.filter(item => item.id !== productId);
    localStorage.setItem('cart', JSON.stringify(this.cartItems));
    this.calculateTotal();
    this.cartService.updateCartCount();
  }

  updateCart(): void 
  {
    localStorage.setItem('cart', JSON.stringify(this.cartItems));
    this.calculateTotal();
    this.cartService.updateCartCount();
  }

}
