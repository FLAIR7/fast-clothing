import ProductController from '../../controllers/productController'
import ProductList from '../../components/product/ProductList'
import { Footer } from '../../components/footer/Footer'

export function Home() {

  const controller = new ProductController();
  return (
      <div>
        <ProductList controller={controller}/>
        <Footer/>
      </div>
  )
}

export default Home