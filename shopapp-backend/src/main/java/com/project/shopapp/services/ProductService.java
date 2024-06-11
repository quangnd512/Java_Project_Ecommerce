package com.project.shopapp.services;

import com.project.shopapp.dtos.ProductDTO;
import com.project.shopapp.dtos.ProductImageDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.InvalidParamException;
import com.project.shopapp.models.Category;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import com.project.shopapp.repositories.CategoryRepository;
import com.project.shopapp.repositories.ProductImageReponsitory;
import com.project.shopapp.repositories.ProductReponsitory;
import com.project.shopapp.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductReponsitory productReponsitory;
    private final CategoryRepository categoryRepository;
    private final ProductImageReponsitory productImageReponsitory;
    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException {
        Category existingCategory = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(() ->
                        new DataNotFoundException("Khong tim thay category voi id la: "
                                +productDTO.getCategoryId()));

        Product newProduct = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .category(existingCategory)
                .build();
        return productReponsitory.save(newProduct);
    }

    @Override
    public Product getProductById(long id) throws Exception {
        return productReponsitory.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannt find product with id la: "+id));
    }

    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
        // Lấy danh sách theo trang (page) và giới hạn (limit)
        return productReponsitory.findAll(pageRequest).map(ProductResponse::fromProduct);
    }

    @Override
    public Product updateProduct(
            long id,
            ProductDTO productDTO
    )
            throws Exception {
        Product existsProduct = getProductById(id);
        if (existsProduct != null) {
            // Copy các thuộc tính từ DTO sang product
            // Có thể sử dụng ModelMapper
            Category existingCategory = categoryRepository
                    .findById(productDTO.getCategoryId())
                    .orElseThrow(() ->
                            new DataNotFoundException("Khong tim thay category voi id la: "
                                    +productDTO.getCategoryId()));
            existsProduct.setName(productDTO.getName());
            existsProduct.setCategory(existingCategory);
            existsProduct.setPrice(productDTO.getPrice());
            existsProduct.setDescription(productDTO.getDescription());
            existsProduct.setThumbnail(productDTO.getThumbnail());
            return productReponsitory.save(existsProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> optionalProduct = productReponsitory.findById(id);
        optionalProduct.ifPresent(productReponsitory::delete);
    }

    @Override
    public boolean existsByName(String name) {
        return productReponsitory.existsByName(name);
//        return false;
    }

    @Override
    public ProductImage createProductImage(
            Long productId,
            ProductImageDTO productImageDTO
    ) throws DataNotFoundException, InvalidParamException {
        Product existingProduct = productReponsitory
                .findById(productId)
                .orElseThrow(() ->
                        new DataNotFoundException("Khong tim thay product voi id la: "
                                +productImageDTO.getProductId()));

        ProductImage newProductImage = ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();

        // Không cho insert quá 5 ảnh 1 sản phẩm
        int size = productImageReponsitory.findByProductId(productId).size();
        if (size >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new InvalidParamException("So anh nho hon hoac bang "+ ProductImage.MAXIMUM_IMAGES_PER_PRODUCT);
        }
        return productImageReponsitory.save(newProductImage);
    }
}
