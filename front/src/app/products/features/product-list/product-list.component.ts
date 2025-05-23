import { Component, OnInit, inject, signal } from "@angular/core";
import { Product } from "app/products/data-access/product.model";
import { ProductsService } from "app/products/data-access/products.service";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { CommonModule } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
import Swal from 'sweetalert2';
import { FormsModule } from "@angular/forms";
import { InputTextModule } from "primeng/inputtext";
import { FilterPipe } from "app/pipes/filter.pipe";
import { InputNumberModule } from "primeng/inputnumber";
import { CartService } from "app/products/data-access/cart.service";


const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule,
            FormsModule, 
            CardModule, 
            ButtonModule,
            DialogModule,
            ProductFormComponent,
            CommonModule, 
            NgxPaginationModule,
            InputTextModule,FilterPipe,InputNumberModule],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService);

  public readonly products = this.productsService.products;

  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);

  page: number = 1;
  searchTerm: string = '';

  constructor(private cartService:CartService)
  {
  }

  ngOnInit() {
    this.productsService.get().subscribe();
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product){
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe();
  }

  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe();
    } else {
      this.productsService.update(product).subscribe();
    }
    this.closeDialog();
  }

  public onCancel()
  {
    this.closeDialog();
  }

  private closeDialog() 
  {
    this.isDialogVisible = false;
  }

  addToCart(product: any): void 
  {
      const quantity = product.selectedQuantity || 1;
      const cart = JSON.parse(localStorage.getItem('cart') || '[]');

      const index = cart.findIndex((p: any) => p.id === product.id);
      if (index !== -1) {
        cart[index].quantity += quantity;
      } else {
        cart.push({ ...product, quantity });
      }

      localStorage.setItem('cart', JSON.stringify(cart));
      product.selectedQuantity = 1; 

      this.cartService.updateCartCount();

        Swal.fire({
          icon: 'success',
          title: 'Ajouté au panier !',
          text: `${product.name} a été ajouté avec succès.`,
          timer: 2000,
          showConfirmButton: false
        });
    }

}
