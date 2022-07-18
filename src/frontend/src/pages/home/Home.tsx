import React, { useEffect, useState } from 'react'
import ProductController from '../../controllers/productController'
import { Container, Item } from './Home-styles'
import { ProductResponseBody } from '../../types/productTypes'
import api from '../../services/api'
import axios, { AxiosPromise, AxiosResponse } from 'axios'
import ProductList from '../../components/product/ProductList'
import { Footer } from '../../components/footer/Footer'
import data from "../../item.json";

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